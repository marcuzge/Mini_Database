package db61b;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

    /** Test basic functionality including:
    * 1. The row class;
    * 2. The table class;
    * 3. Conditions;
    */
public class BasicTests {

    @Test
    public void testRow() {
        Row r = new Row(new String[]{"I", "am", "writing", "this", "test."});
        Row r1 = new Row(new String[]{"He", "is", "handsome."});
        Row r2 = new Row(new String[]{"She", "is", "beautiful."});
        assertEquals(5, r.size());
        assertEquals("am", r.get(1));
        assertEquals(true, r.equals(r));
        assertEquals(false, r.equals(r1));
        assertEquals(false, r.equals(r2));
    }

    Table table1 = Table.readTable("enrolled");
    Table table2 = Table.readTable("students");

    @Test
    public void testTableSize() {
        assertEquals(19, table1.size());
        assertEquals(6, table2.size());
    }

    @Test
    public void testReadWriteTable() {
        assertEquals("SID", table1.title(0));
        assertEquals("CCN", table1.title(1));
        assertEquals("Grade", table1.title(2));
        assertEquals(0, table2.columnIndex("SID"));
        assertEquals(1, table2.columnIndex("Lastname"));
        assertEquals(2, table2.columnIndex("Firstname"));
        table1.writeTable("enrolled1");
        table2.writeTable("students1");
    }

    @Test
    public void testTablePrint() {
        Table table3 = new Table("table3", new String[]{"1", "2", "3"});
        table3.add(new Row(new String[]{"one", "two", "three"}));
        table3.add(new Row(new String[]{"one", "two", "three"}));
        table3.print();
    }

    @Test
    public void testConditions() {
        ArrayList<TableIterator> tables = new ArrayList<>();
        Table table4 = new Table("table4", new String[]{"1", "2", "3"});
        table4.add(new Row(new String[]{"one", "two", "three"}));
        table4.add(new Row(new String[]{"do", "re", "mi"}));
        TableIterator iterator = table4.tableIterator();
        Column column1 = new Column(table4, "1");
        Column column2 = new Column(table4, "2");
        tables.add(iterator);
        column2.resolve(tables);
        column1.resolve(tables);
        Condition cond = new Condition(column1, "=", column2);
        assertEquals(false, cond.test());
        cond = new Condition(column1, "<=", column2);
        assertEquals(true, cond.test());
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(BasicTests.class));
    }
}
