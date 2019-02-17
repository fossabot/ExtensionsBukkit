package ml.extbukkit.main.entity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonObject;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.util.Wrapper;
import ml.extbukkit.api.world.StraightDirection;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.world.Position;
import org.bukkit.entity.Player;

public class Entity extends Wrapper<Player> implements IEntity
{

    public Entity(Player handle)
    {
        super( handle );
    }

    @Override
    public boolean addPassenger(IEntity passenger)
    {
        // TODO
        return false;
    }

    @Override
    public boolean ejectPassengers()
    {
        // TODO
        return false;
    }

    @Override
    public void getBoundingBox()
    {

    }

    @Override
    public int getId()
    {
        // TODO
        return 0;
    }

    @Override
    public StraightDirection getDirection()
    {
        // TODO
        return null;
    }

    @Override
    public int getFireTicks()
    {
        // TODO
        return 0;
    }

    @Override
    public double getHeight()
    {
        // TODO
        return 0;
    }

    @Override
    public void getDamageEvent()
    {
        // TODO
    }

    @Override
    public int getMaxFireTicks()
    {
        // TODO
        return 0;
    }

    @Override
    public List<IEntity> getPassengers()
    {
        // TODO
        return null;
    }

    @Override
    public int getPortalCooldown()
    {
        // TODO
        return 0;
    }

    @Override
    public int getTicksExist()
    {
        // TODO
        return 0;
    }

    @Override
    public IEntityType getType()
    {
        // TODO
        return null;
    }

    @Override
    public UUID getUUID()
    {
        return handle.getUniqueId();
    }

    @Override
    public IEntity getPassengerOf()
    {
        // TODO
        return null;
    }

    @Override
    public double getWidth()
    {
        // TODO
        return 0;
    }

    @Override
    public boolean isMarkedForRemoval()
    {
        // TODO
        return false;
    }

    @Override
    public boolean hasPassengers()
    {
        // TODO
        return false;
    }

    @Override
    public boolean isPassenger()
    {
        // TODO
        return false;
    }

    @Override
    public boolean isGlowing()
    {
        // TODO
        return false;
    }

    @Override
    public boolean isExist()
    {
        // TODO
        return false;
    }

    @Override
    public boolean ejectAsPassenger()
    {
        // TODO
        return false;
    }

    @Override
    public void remove()
    {
        // TODO
    }

    @Override
    public boolean removePassenger(IEntity passenger)
    {
        // TODO
        return false;
    }

    @Override
    public void setFireTicks(int ticks)
    {
        // TODO
    }

    @Override
    public void setDamageEvent(Object event)
    {
        // TODO
    }

    @Override
    public boolean setPassenger(IEntity passenger)
    {
        // TODO
        return false;
    }

    @Override
    public void setTicksExist(int exist)
    {
        // TODO
    }

    @Override
    public void sendMessage(String message)
    {
        handle.sendMessage( message );
    }

    @Override
    public void sendMessages(String... message)
    {
        Arrays.stream( message ).forEach( this::sendMessage );
    }

    @Override
    public String getName()
    {
        return handle.getName();
    }

    @Override
    public boolean hasPermission(String permission)
    {
        return handle.hasPermission( permission );
    }

    @Override
    public JsonObject getNBT()
    {
        // TODO
        return null;
    }

    @Override
    public void updateNBT(JsonObject nbt)
    {
        // TODO
    }

    @Override
    public Position getPosition()
    {
        return new Position( handle.getLocation() );
    }

    @Override
    public boolean setPosition(Position position)
    {
        // TODO
        return false;
    }

}