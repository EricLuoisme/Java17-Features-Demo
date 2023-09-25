package record;

/**
 * @author Roylic
 * 2023/9/25
 */
public record WashDishesRecord(String title, String author, boolean finished) {
    public String recordDescription() {
        return title + " is " + (finished ? "finished" : "not finished") + " by " + author;
    }
}
