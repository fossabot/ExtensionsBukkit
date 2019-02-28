package ml.extbukkit.main.secure.connection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageSerializer;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.IScheduledTask;
import ml.extbukkit.api.scheduler.ITask;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.api.util.Time;
import ml.extbukkit.main.secure.nms.reflection.NMSRUtil;
import ml.extbukkit.main.secure.world.entity.Entity;
import ml.extbukkit.main.server.Server;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SimpleExtensionPlayer extends Entity implements ExtensionedPlayer
{
    private Player base;
    private IServer server = Server.getInstance();
    private ChatMessageSerializer cmSerializer = ChatMessageSerializer.getInstance();

    public SimpleExtensionPlayer(Player handle)
    {
        super( handle );
        this.base = handle;
    }

    public SimpleExtensionPlayer(String name)
    {
        this( Bukkit.getPlayer( name ) );
    }

    public SimpleExtensionPlayer(UUID uuid)
    {
        this ( Bukkit.getPlayer( uuid ) );
    }

    @Override
    public String getDisplayName()
    {
        return base.getDisplayName();
    }

    @Override
    public void setDisplayName(String displayName)
    {
        base.setDisplayName( displayName );
    }

    @Override
    public void setPlayerListName(String name)
    {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam( base.getName() );
        if ( team == null )
        {
            team = board.registerNewTeam( base.getName() );
        }
        team.setDisplayName( name );
        team.addEntry( base.getName() );
    }

    @Override
    public boolean isSneaking()
    {
        return base.isSneaking();
    }

    @Override
    public void sendTitle(String title, String subtitle)
    {
        sendTitle( title, subtitle, 20, 80, 20 );
    }

    @Override
    public void sendTitle(ChatMessage title, ChatMessage subTitle)
    {
        sendTitle( title, subTitle, 20, 80, 20 );
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut)
    {
        base.sendTitle( title, subtitle, fadeIn, stay, fadeOut );
    }

    @Override
    public void sendTitle(ChatMessage title, ChatMessage subtitle, int fadeIn, int stay, int fadeOut)
    {
        // Unlike TSEngineer I care about the version support, that's why i've got to reflection (MrIvanPlays)
        try
        {
            Class<?> packetClass = NMSRUtil.getNMSClass( "PacketPlayOutTitle" );
            Class<?> icbcClass = NMSRUtil.getNMSClass( "IChatBaseComponent" );
            Class<?> enumTitleActionClass = packetClass.getClasses()[0];
            Constructor<?> packetConstructor = packetClass.getDeclaredConstructor( enumTitleActionClass, icbcClass );
            packetConstructor.setAccessible( true );
            Class<?> chatSerializerClass = icbcClass.getClasses()[0];
            Method chatSerializerM = chatSerializerClass.getDeclaredMethod( "a", String.class );
            chatSerializerM.setAccessible( true );
            Object tIcbc = chatSerializerM.invoke( chatSerializerClass, cmSerializer.toString( title ) );
            Object stIcbc = chatSerializerM.invoke( chatSerializerClass, cmSerializer.toString( subtitle ) );
            Object packetTitle = packetConstructor.newInstance( enumTitleActionClass.getEnumConstants()[0], tIcbc );
            Object packetSubtitle = packetConstructor.newInstance( enumTitleActionClass.getEnumConstants()[1], stIcbc );
            Constructor<?> timesConstructor = packetClass.getDeclaredConstructor( int.class, int.class, int.class );
            timesConstructor.setAccessible( true );
            Object packetTimings = timesConstructor.newInstance( fadeIn, stay, fadeOut );
            Method sendPacket = NMSRUtil.getPlayerConnection( base ).getClass().getDeclaredMethod( "sendPacket", NMSRUtil.getNMSClass( "Packet" ) );
            sendPacket.setAccessible( true );
            sendPacket.invoke( NMSRUtil.getPlayerConnection( base ), packetTimings );
            sendPacket.invoke( NMSRUtil.getPlayerConnection( base ), packetTitle );
            sendPacket.invoke( NMSRUtil.getPlayerConnection( base ), packetSubtitle );
        } catch ( InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | NoSuchFieldException | ClassNotFoundException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendActionbar(String message)
    {
        sendActionbar( new ChatMessage( message ) );
    }

    @Override
    public void sendActionbar(ChatMessage message)
    {
        base.spigot().sendMessage( ChatMessageType.ACTION_BAR,  ComponentSerializer.parse( cmSerializer.toString( message ) ) );
    }

    @Override
    public void sendActionbar(String message, AExtension extension, int stayTime)
    {
        sendActionbar( new ChatMessage( message ), extension, stayTime );
    }

    @Override
    public void sendActionbar(ChatMessage message, AExtension extension, int stayTime)
    {
        new ITask()
        {
            IScheduledTask task = server.getSchedulerManager().schedule( extension, this, new Time( 0 ), new Time( 20 ) );
            int stayed;

            @Override
            public void execute()
            {
                if ( stayed == stayTime )
                {
                    task.cancel();
                }
                else
                {
                    stayed++;
                    sendActionbar( message );
                }
            }
        };
    }

}