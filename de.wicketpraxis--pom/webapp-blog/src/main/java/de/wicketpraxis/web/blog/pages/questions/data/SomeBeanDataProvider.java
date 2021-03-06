package de.wicketpraxis.web.blog.pages.questions.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SomeBeanDataProvider implements ISortableDataProvider<SomeBean,String>, IFilterStateLocator<SomeBean> {

	ISortState<String> _sortState = new SingleSortState<String>();
	SomeBean _filter = new SomeBean();

	final static int LIST_SIZE = 123;
	private List<SomeBean> _list;

	@Override
	public Iterator<? extends SomeBean> iterator(long lfirst, long lcount) {
		initList();
		
		int first=(int) lfirst;
		int count=(int) lcount;

		List<SomeBean> ret = _list;
		if (ret.size() > (first + count)) {
			ret = ret.subList(first, first + count);
		}

		return ret.iterator();
	}

	@Override
	public IModel<SomeBean> model(SomeBean object) {
		return Model.of(object);
	}

	@Override
	public long size() {
		initList();

		return _list.size();
	}

	@Override
	public void detach() {
		_list = null;
	}

	@Override
	public ISortState<String> getSortState() {
		return _sortState;
	}

	public void setSortState(ISortState<String> state) {
		_sortState = state;
	}

	@Override
	public SomeBean getFilterState() {
		return _filter;
	}

	@Override
	public void setFilterState(SomeBean state) {
		_filter = state;
	}

	private void initList() {
		if (_list == null) {
			final SortOrder nameSort;
			final SortOrder alterSort;
			if (_sortState != null) {
				nameSort = _sortState.getPropertySortOrder("name");
				alterSort = _sortState.getPropertySortOrder("alter");
			} else {
				nameSort = SortOrder.NONE;
				alterSort = SortOrder.NONE;
			}

			_list = getSortedList(nameSort, alterSort, _filter);
		}
	}

	private List<SomeBean> getSortedList(final SortOrder nameSort, final SortOrder alterSort, SomeBean filter) {
		List<SomeBean> result = SomeBeanGenerator.getBeans(LIST_SIZE, filter);

		Collections.sort(result, new Comparator<SomeBean>() {

			public int compare(SomeBean o1, SomeBean o2) {
				int compName = o1.getName().compareTo(o2.getName());
				int compAlter = new Integer(o1.getAlter()).compareTo(o2.getAlter());
				switch (nameSort) {
					case NONE:
						compName = 0;
						break;
					case DESCENDING:
						compName = -compName;
						break;
				}
				switch (alterSort) {
					case NONE:
						compAlter = 0;
						break;
					case DESCENDING:
						compAlter = -compAlter;
						break;
				}
				if (compName != 0)
					return compName;
				return compAlter;
			}
		});

		return result;
	}

}
