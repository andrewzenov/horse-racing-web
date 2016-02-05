package by.training.java.grodno.az.webapp.page.ratelinepage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import by.training.java.grodno.az.data.model.RateLine;
import by.training.java.grodno.az.service.CoefficientService;
import by.training.java.grodno.az.service.RateLineService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.coefficientpage.CoefficientEditPage;

@AuthorizeInstantiation(value = { "admin" })
public class RateLinePage extends AbstractPage {

	private static final long serialVersionUID = 1L;

	@Inject
	private RateLineService rateLineService;

	@Inject
	private CoefficientService coefficientService;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<RateLine> rateLines = rateLineService.getAll();

		add(new ListView<RateLine>("rate-line-list", rateLines) {
			@Override
			protected void populateItem(ListItem<RateLine> item) {

				final RateLine rateLine = item.getModelObject();
				item.add(new Label("id", rateLine.getId()));
				item.add(new Label("title", rateLine.getTitle()));
				item.add(new Label("positions", rateLine.getPositions()));
				item.add(new Label("description", rateLine.getDescription()));

				item.add(new Link("rate-line-edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new RateLineEditPage(rateLine));
					}
				});

				item.add(new Link("rate-line-delete-link") {

					@Override
					public void onClick() {
						if (canBeDelete(rateLine)) {
							rateLineService.delete(rateLine);
							setResponsePage(RateLinePage.class);
						} else {
							RateLinePage responsePage = new RateLinePage();
							responsePage.error(getString("page.rateLinePage.not.delete"));
							setResponsePage(responsePage);
						}
					}
				});

			}
		});

		add(new Link("rate-line-create-link") {
			@Override
			public void onClick() {
				if (rateLines.size() < CoefficientEditPage.MAXQUANTITY) {
					setResponsePage(new RateLineEditPage());
				} else {
					AbstractPage responsePage = new RateLinePage();
					warn(getString("all.tableRecords.limit"));
					setResponsePage(responsePage);
				}
			}
		});
	}

	private boolean canBeDelete(RateLine rateLine) {
		boolean result = false;
		Map<String, Object> findingParemeters = new HashMap<>();
		findingParemeters.put("rateLineId", rateLine.getId());
		int count = coefficientService.getCount(findingParemeters);
		if (count == 0) {
			result = true;
		}
		return result;
	}

}
