package edu.illinois.cs.cs125.spring2019.mp3.lib;
//import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
//import com.google.gson.JsonArray;

/**
 *recognizephoto.
 */
public class RecognizePhoto {
    /**
     * constructor.
     */
    public RecognizePhoto() {

    }

    /**
     * Get the image width.
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the width of the image or 0 on failure
     */
    public static int getWidth(final java.lang.String json) {
        if (json == null) {
            return 0;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject metadata = result.get("metadata").getAsJsonObject();
        int width = metadata.get("width").getAsInt();
        return width;
    }

    /**
     * Get the image height.
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the height of the image or 0 on failure
     */
    public static int getHeight(final java.lang.String json) {
        if (json == null) {
            return 0;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject metadata = result.get("metadata").getAsJsonObject();
        int height = metadata.get("height").getAsInt();
        return height;
    }

    /**
     * Get the image file type.
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the type of the image or null
     */
    public static java.lang.String getFormat(final java.lang.String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject metadata = result.get("metadata").getAsJsonObject();
        java.lang.String format = metadata.get("format").getAsString();
        return format;
    }

    /**
     * Return the caption describing the image created by the Microsoft Cognitive Services API.
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @return the caption describing the image created by the Microsoft or null on failure
     */
    public static java.lang.String getCaption(final java.lang.String json) {
        if (json == null) {
            return null;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonObject description = result.get("description").getAsJsonObject();
        JsonArray captions = description.get("captions").getAsJsonArray();
        java.lang.String text = captions.get(0).getAsJsonObject().get("text").getAsString();
        return text;
    }

    /**
     *Determine whether the image contains a dog.
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @param minConfidence the minimum confidence required for this determination
     * @return a boolean indicating whether the image contains a dog or false on failure
     */
    public static boolean isADog(final java.lang.String json,
                                 final double minConfidence) {
        if (json == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonArray tags = result.get("tags").getAsJsonArray();
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getAsJsonObject().get("name").getAsString().equals("dog")) {
                if (tags.get(i).getAsJsonObject().get("confidence").getAsDouble() > minConfidence) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determine whether the image contains a cat.
     * @param json the JSON string returned by the Microsoft Cognitive Services API
     * @param minConfidence the minimum confidence required for this determination
     * @return a boolean indicating whether the image contains a cat or false on failure
     */
    public static boolean isACat(final java.lang.String json,
                                 final double minConfidence) {
        if (json == null) {
            return false;
        }
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        JsonArray tags = result.get("tags").getAsJsonArray();
        for (int i = 0; i < tags.size(); i++) {
            if (tags.get(i).getAsJsonObject().get("name").getAsString().equals("cat")) {
                if (tags.get(i).getAsJsonObject().get("confidence").getAsDouble() > minConfidence) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if image contains Rick Astley.
     * @param json the JSON returned by the Microsoft Cognitive Services API
     * @return true if you've Rickrolled yourself
     */
    public static boolean isRick(final java.lang.String json) {
        return true;
    }

}
