package model.response;

/**
 * Metadata describing pagination information when returning lists of data in
 * responses.
 * 
 * @author Patrick.Doody
 *
 */
public class Pagination {
	public Integer count;
	public Integer page;
	public Integer per_page;

	public Pagination() {

	}

	public Pagination(Integer count, Integer page, Integer per_page) {
		this.count = count;
		this.page = page;
		this.per_page = per_page;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPer_page() {
		return per_page;
	}
}
