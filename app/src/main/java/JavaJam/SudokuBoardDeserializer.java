package JavaJam;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

class SudokuBoardDeserializer implements JsonDeserializer {
    public SudokuBoard deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
        SudokuCells cells;
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray jBoard = jsonObject.get("board").getAsJsonArray();
        cells = context.deserialize(jBoard, SudokuCells.class);

        SudokuBoard board = new SudokuBoard(cells);
        return board;
    }
}

class SudokuRowDeserializer implements JsonDeserializer<SudokuCells> {
    public SudokuCells deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
        SudokuCells cells = new SudokuCells();
        for (JsonElement col: json.getAsJsonArray()) {
            ArrayList<SudokuCell> colCells = context.deserialize(col, ArrayList.class);
            cells.add(colCells);
        }
        return cells;
    }
}

class SudokuColDeserializer implements JsonDeserializer<ArrayList<SudokuCell>> {
    public ArrayList<SudokuCell> deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<SudokuCell> sc = new ArrayList<>();
        for (JsonElement cell: json.getAsJsonArray()) {
            JsonObject cellObj = cell.getAsJsonObject();
            sc.add(
                new SudokuCell(
                    cellObj.get("value").getAsInt(),
                    cellObj.get("row").getAsInt(),
                    cellObj.get("column").getAsInt()
                )
            );
        }
        return sc;
    }
}