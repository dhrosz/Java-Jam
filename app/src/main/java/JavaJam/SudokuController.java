package JavaJam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuController {

    @RequestMapping("/sudoku")
    public String getBody(@RequestBody() String board) {
        SudokuBoard GameSpace = new SudokuBoard(board);
        return "This has been a test of the emergency broadcast system.";
    }
}

