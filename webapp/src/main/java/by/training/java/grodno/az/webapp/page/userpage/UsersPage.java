package by.training.java.grodno.az.webapp.page.userpage;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import by.training.java.grodno.az.data.model.User;
import by.training.java.grodno.az.service.UserService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.registrationpage.RegistrationPage;

@AuthorizeInstantiation(value = { "admin" })
public class UsersPage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		UsersDataProvider usersDataProvider = new UsersDataProvider();
		DataView<User> dataView = new DataView<User>("users-list", usersDataProvider, 5) {
			@Override
			protected void populateItem(Item<User> item) {
				final User user = item.getModelObject();
				item.add(new Label("id"));
				// item.add(new Label("fName", user.getFirstName()));
				// item.add(new Label("lName", user.getLastName()));
				item.add(new Label("login"));
				item.add(new Label("firstName"));
				item.add(new Label("lastName"));
				item.add(new Label("email"));
				item.add(new Label("createDate"));
				item.add(new Label("role"));

				item.add(new Link("user-edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new RegistrationPage(user));
					}
				});

				item.add(new Link("user-delete-link") {
					@Override
					public void onClick() {
						userService.delete(user);
						setResponsePage(UsersPage.class);
					}
				});

			}
		};
		add(dataView);

		add(new OrderByBorder<Object>("sortId", "id", usersDataProvider));
		add(new OrderByBorder<Object>("sortfName", "firstName", usersDataProvider));
		add(new PagingNavigator("paging", dataView));

		add(new Link("user-create-link") {
			@Override
			public void onClick() {
				setResponsePage(new RegistrationPage());
			}
		});

	}

	private class UsersDataProvider extends SortableDataProvider<User, Object> {

		public UsersDataProvider() {
			super();
			setSort("id", SortOrder.ASCENDING);
		}

		@Override
		public Iterator<? extends User> iterator(long first, long count) {

			SortParam<Object> sort = getSort();
			ISortState<Object> sortState = getSortState();
			SortOrder currentSort = sortState.getPropertySortOrder(sort.getProperty());

			// TODO sort in service
			return userService.getAll((int) first, (int) count).iterator();
		}

		@Override
		public long size() {
			return userService.getCount();
		}

		@Override
		public IModel<User> model(User object) {
			return new CompoundPropertyModel<>(object);
		}

	}
}
