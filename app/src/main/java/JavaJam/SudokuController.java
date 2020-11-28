package JavaJam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@RestController
public class SudokuController {

    @RequestMapping("/sudoku")
    public String getBody(@RequestBody() String input) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(SudokuBoard.class, new SudokuBoardDeserializer())
                .create();
        SudokuBoard gameSpace = gson.fromJson(input, SudokuBoard.class);
        String Breakpoint = "";
        //SudokuBoard GameSpace = new SudokuBoard(board);
        return "This has been a test of the emergency broadcast system.";
    }
}

