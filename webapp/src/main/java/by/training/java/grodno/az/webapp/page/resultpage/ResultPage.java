package by.training.java.grodno.az.webapp.page.resultpage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.googlecode.wicket.kendo.ui.markup.html.link.BookmarkablePageLink;

import by.training.java.grodno.az.data.entities.RacingLineView;
import by.training.java.grodno.az.data.model.HorseRacing;
import by.training.java.grodno.az.data.model.RacingLine;
import by.training.java.grodno.az.service.RacingLineService;
import by.training.java.grodno.az.webapp.page.abstractpage.AbstractPage;
import by.training.java.grodno.az.webapp.page.admin.horseracingpage.HorseRacingPage;

public class ResultPage extends AbstractPage {
	private static final long serialVersionUID = 1L;

	@Inject
	private RacingLineService racingLineService;

	private HorseRacing horseRacing;

	public ResultPage(HorseRacing hourseRacing) {
		super();
		this.horseRacing = hourseRacing;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Label("hourse-racing-title", horseRacing.toString()));

		Set<Integer> particioantCheckSet = new HashSet<>();
		Map<String, Object> atributes = new HashMap<>();
		atributes.put("horseRacingId", horseRacing.getId());
		List<RacingLine> racings = racingLineService.getAll(atributes, "id", true);
		int listSize = racings.size();
		List<RacingLineView> racingLineViewsList = new ArrayList<>(listSize);
		for (RacingLine rl : racings) {
			racingLineViewsList.add(racingLineService.getView(rl));
			particioantCheckSet.add(rl.getParticipantId());
		}

		add(new ListView<RacingLineView>("racing-line-list", racingLineViewsList) {
			@Override
			protected void populateItem(ListItem<RacingLineView> item) {

				final RacingLineView racingLineView = item.getModelObject();
				item.add(new Label("id", racingLineView.getRacingLineId()));
				item.add(new Label("jockey-name", racingLineView.getParticipantView().getJockeyFirstName()));
				item.add(new Label("hourse-name", racingLineView.getParticipantView().getHorseName()));
				item.add(new Label("result", racingLineView.getRusult()));
			}
		});

		add(new BookmarkablePageLink<Void>("hourse-racing-page-link", HorseRacingPage.class));
	}
}
