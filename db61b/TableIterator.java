package db61b;

import java.util.Iterator;

/** An iterator through the rows of a Table.  Rather than providing the
 *  usual Java Iterator interface, a TableIterator acts more like a C++
 *  STL iterator: at any given time, there is a notion of the current
 *  Row and a separate method to proceed to the next.
 *  @author Zixuan Ge
 */
class TableIterator {

    /** An STL-style iterator through the rows of TABLE. */
    TableIterator(Table table) {
        _table = table;
        reset();
    }

    /** Reinitialize me to the first row. */
    void reset() {
        _rows = _table.iterator();
        if (!_rows.hasNext()) {
            _row = null;
        } else {
            _row = _rows.next();
        }
    }

    /** Return the Table over which I am iterating. */
    Table table() {
        return _table;
    }

    /** Return true iff there is a current Row (that is, we have not
     *  exhausted the iterator). */
    boolean hasRow() {
        return _row != null;
    }

    /** Proceed to the next row.  Assumes that hasRow() is true.  Return
     *  the new value of hasRow(). */
    Row next() {
        if (!_rows.hasNext()) {
            _row = null;
        } else {
            _row = _rows.next();
        }
        return _row;
    }

    /** Return the index of column NAME in my Table, or -1 if there is no
     *  such column. */
    int columnIndex(String name) {
        return _table.columnIndex(name);
    }

    /** Return the value of column #K in the current row. */
    String value(int k) {
        if (!hasRow()) {
            throw new IllegalStateException("Past the last row.");
        }
        return _row.get(k);
    }

    /** Get table. @return _table */
    Table getTable() {
        return _table;
    }

    /** My table. */
    private final Table _table;
    /** The current Row of my Table, or null if there are no more. */
    private Row _row;
    /** My rows. */
    private Iterator<Row> _rows;

}
