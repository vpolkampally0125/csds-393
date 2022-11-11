import com.google.gson.*
import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.util.*

abstract class JsonUpdateable(inputString : String, inputHeading : String) {
    var jsonHeading : String
    var fileUpdated : File
    var parser : JsonParser
    var root : JsonObject
    var specRoot : JsonObject
    var gson : Gson

    abstract fun updateJson() : Boolean

    fun verifyJsonHeading(dataObj : JsonObject, jsonHeading : String) : Boolean {
        return dataObj.has(jsonHeading)
    }

    init {
        parser = JsonParser()
        fileUpdated = File(inputString)
        root = parser.parse(FileReader(fileUpdated)).asJsonObject
        jsonHeading = inputHeading
        specRoot = root.get(jsonHeading).asJsonObject
        gson = Gson()

        if(!verifyJsonHeading(root, jsonHeading))
            throw Exception("That was not a valid Json heading.")
    }
}