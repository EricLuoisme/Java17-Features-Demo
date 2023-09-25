package record;

/**
 * Record pattern
 *
 * @author Roylic
 * 2023/9/25
 */
public class RecordStuff {

    public static void main(String[] args) {

        // record would force all-args constructor
        WashDishesRecord record = new WashDishesRecord("Wash Dishes", "Tom", true);

        // record could not be changed, only have get function
        System.out.println(record.title());

        // can define functions inside the record class
        System.out.println(record.recordDescription());
    }

}
