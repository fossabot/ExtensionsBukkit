package ml.extbukkit.main.secure.chat;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ml.extbukkit.api.chat.ChatMessage;

public class ChatMessageSerializer implements JsonSerializer<ChatMessage>, JsonDeserializer<ChatMessage>
{

    @Override
    public JsonElement serialize(ChatMessage component, Type type, JsonSerializationContext context)
    {
        JsonObject object = new JsonObject();
        object.addProperty( "text", component.getMessage() );
        if ( component.getBold() != null )
        {
            object.addProperty( "bold", component.getBold() );
        }

        if ( component.getItalic() != null )
        {
            object.addProperty( "italic", component.getItalic() );
        }

        if ( component.getUnderlined() != null )
        {
            object.addProperty( "underlined", component.getUnderlined() );
        }

        if ( component.getStrikeout() != null )
        {
            object.addProperty( "strikethrough", component.getStrikeout() );
        }

        if ( component.getObfuscated() != null )
        {
            object.addProperty( "obfuscated", component.getObfuscated() );
        }

        return object;
    }

    @Override
    public ChatMessage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject object = jsonElement.getAsJsonObject();
        ChatMessage component = new ChatMessage( object.get( "text" ).getAsString() );
        if ( object.has( "bold" ) )
        {
            component.setBold( object.get( "bold" ).getAsBoolean() );
        }
        if ( object.has( "italic" ) )
        {
            component.setItalic( object.get( "italic" ).getAsBoolean() );
        }
        if ( object.has( "underlined" ) )
        {
            component.setUnderlined( object.get( "underlined" ).getAsBoolean() );
        }
        if ( object.has( "strikethrough" ) )
        {
            component.setStrikeout( object.get( "strikethrough" ).getAsBoolean() );
        }
        if ( object.has( "obfuscated" ) )
        {
            component.setObfuscated( object.get( "obfuscated" ).getAsBoolean() );
        }
        return component;
    }

}