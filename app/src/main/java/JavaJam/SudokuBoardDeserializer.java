package JavaJam;

import com.google.gson.*;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

class SudokuBoardDeserializer implements JsonDeserializer {
    public SudokuBoard deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        SudokuCells cells = new SudokuCells();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jBoard = jsonObject.get("board");

        SudokuRowDeserializer srd = new SudokuRowDeserializer();
        cells = srd.deserialize(jBoard, SudokuCells.class, context);

        SudokuBoard board = new SudokuBoard(cells);
        return board;
    }
}

class SudokuRowDeserializer implements JsonDeserializer<SudokuCells> {
    public SudokuCells deserialize(JsonElement json, Type TypeOfT, JsonDeserializationContext context) throws JsonParseException {
        SudokuCells cells = new SudokuCells();
        SudokuColDeserializer scd = new SudokuColDeserializer();
        for (JsonElement col: json.getAsJsonArray()) {
            cells.add(scd.deserialize(col, ArrayList.class, context));
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