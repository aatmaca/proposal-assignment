package ayas.displaytag;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.export.TextExportView;
import org.displaytag.model.Column;
import org.displaytag.model.ColumnIterator;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.RowIterator;
import org.displaytag.model.TableModel;

public abstract class BaseExportView implements TextExportView {

    private static Log log = LogFactory.getLog(BaseExportView.class);
    private TableModel model;
    private boolean exportFull;
    private boolean header;
    private boolean decorated;

    public void setParameters(TableModel tableModel, boolean exportFullList, boolean includeHeader,
	    boolean decorateValues) {
	this.model = tableModel;
	this.exportFull = exportFullList;
	this.header = includeHeader;
	this.decorated = decorateValues;
    }

    protected String getRowStart() {
	return null;
    }

    protected String getRowEnd() {
	return null;
    }

    protected String getCellStart() {
	return null;
    }

    protected abstract String getCellEnd();

    protected String getDocumentStart() {
	return null;
    }

    protected String getDocumentEnd() {
	return null;
    }

    protected abstract boolean getAlwaysAppendCellEnd();

    protected abstract boolean getAlwaysAppendRowEnd();

    protected abstract String escapeColumnValue(Object value);

    protected String doHeaders() {

	final String ROW_START = getRowStart();
	final String ROW_END = getRowEnd();
	final String CELL_START = getCellStart();
	final String CELL_END = getCellEnd();
	final boolean ALWAYS_APPEND_CELL_END = getAlwaysAppendCellEnd();

	StringBuffer buffer = new StringBuffer(1000);

	Iterator iterator = this.model.getHeaderCellList().iterator();

	// start row
	if (ROW_START != null) {
	    buffer.append(ROW_START);
	}

	while (iterator.hasNext()) {
	    HeaderCell headerCell = (HeaderCell) iterator.next();

	    String columnHeader = headerCell.getTitle();

	    if (columnHeader == null) {
		columnHeader = StringUtils.capitalize(headerCell.getBeanPropertyName());
	    }

	    columnHeader = escapeColumnValue(columnHeader);

	    if (CELL_START != null) {
		buffer.append(CELL_START);
	    }

	    if (columnHeader != null) {
		buffer.append(columnHeader);
	    }

	    if (CELL_END != null && (ALWAYS_APPEND_CELL_END || iterator.hasNext())) {
		buffer.append(CELL_END);
	    }
	}

	// end row
	if (ROW_END != null) {
	    buffer.append(ROW_END);
	}

	return buffer.toString();

    }

    public void doExport(Writer out) throws IOException, JspException {
	if (log.isDebugEnabled()) {
	    log.debug(getClass().getName());
	}

	final String DOCUMENT_START = getDocumentStart();
	final String DOCUMENT_END = getDocumentEnd();
	final String ROW_START = getRowStart();
	final String ROW_END = getRowEnd();
	final String CELL_START = getCellStart();
	final String CELL_END = getCellEnd();
	final boolean ALWAYS_APPEND_CELL_END = getAlwaysAppendCellEnd();
	final boolean ALWAYS_APPEND_ROW_END = getAlwaysAppendRowEnd();

	// document start
	write(out, DOCUMENT_START);

	if (this.header) {
	    write(out, doHeaders());
	}

	// get the correct iterator (full or partial list according to the exportFull field)
	RowIterator rowIterator = this.model.getRowIterator(this.exportFull);

	// iterator on rows
	while (rowIterator.hasNext()) {
	    Row row = rowIterator.next();

	    if (this.model.getTableDecorator() != null) {

		String stringStartRow = this.model.getTableDecorator().startRow();
		write(out, stringStartRow);
	    }

	    // iterator on columns
	    ColumnIterator columnIterator = row.getColumnIterator(this.model.getHeaderCellList());

	    write(out, ROW_START);

	    while (columnIterator.hasNext()) {
		Column column = columnIterator.nextColumn();

		// Get the value to be displayed for the column
		String value = escapeColumnValue(column.getValue(this.decorated));

		write(out, CELL_START);

		write(out, value);

		if (ALWAYS_APPEND_CELL_END || columnIterator.hasNext()) {
		    write(out, CELL_END);
		}

	    }
	    if (ALWAYS_APPEND_ROW_END || rowIterator.hasNext()) {
		write(out, ROW_END);
	    }
	}

	// document end
	write(out, DOCUMENT_END);

    }

    private void write(Writer out, String string) throws IOException {
	if (string != null) {
	    out.write(string);
	}
    }

    public boolean outputPage() {
	return false;
    }
}


