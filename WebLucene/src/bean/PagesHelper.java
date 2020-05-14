package bean;

/**
 *
 */
public class PagesHelper {
	private String _tablename = "";
	private String _primary = "";
	private String _columnname = "";
	private String _filter = "";
	private String _order = "";
	private int _currentIndex = 0; 
	private int _pagesize = 10;

	public void setTableName(String _tablename) {
		this._tablename = _tablename;
	}

	public void setPrimary(String _primary) {
		this._primary = _primary;
	}

	public void setCurrentIndex(int _currentIndex) {
		this._currentIndex = _currentIndex;
	}

	public void setPageSize(int _pagesize) {
		this._pagesize = _pagesize;
	}

	public void setColumnName(String _columnname) {
		this._columnname = _columnname;
	}

	public void setFilter(String _filter) {
		this._filter = _filter;
	}

	public void setOrder(String _order) {
		this._order = _order;
	}

	public String ToListString() {
		_order = _order == "" ? _primary + " DESC" : _order;
		String SQLPage = "select TOP " + _pagesize
				+ "  *   from (select ROW_NUMBER() over(order by " + _order
				+ ") RowIndex,";
		SQLPage += " " + _columnname + " from  " + _tablename + " where 1=1  "
				+ _filter
				+ ") as PageSQLTemp where PageSQLTemp.RowIndex between "
				+ _currentIndex + " and " + (_currentIndex + _pagesize);
		return SQLPage;
	}

	public String ToCountString() {
		return "select count(1) from " + _tablename + " where 1=1 " + _filter;
	}
}
