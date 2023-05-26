package game.deserializer;

import com.google.gson.Gson;

import java.io.IOException;

public class Deserializer {
    /**
     * Deserializes a JSON string to an array of objects of the given type.
     *
     * @param input the JSON string to deserialize.
     * @param clazz the class of the objects to deserialize to.
     * @param <T>   the type of the objects to deserialize to.
     * @return an array of objects of the given type.
     * @throws IOException if an I/O error occurs while deserializing the JSON string.
     */
    public static <T> T[] Deserialize(String input, Class<T[]> clazz) throws IOException {
        T[] object;

        Gson gson = new Gson();

        object = gson.fromJson(input, clazz);

        return object;
    }
}