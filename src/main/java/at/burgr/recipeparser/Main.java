package at.burgr.recipeparser;

import java.util.List;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws Exception {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

		if (args.length < 1) {
			LOG.info("Usage: java -jar https://www.chefkoch.de/rezepte/kategorien/");
			return;
		}

		List<String> categoryList = Collector.collect(args[0], ".category-level-2 a", e -> e.absUrl("href"));

		for (String category : categoryList) {
			List<String> recipeList = Collector.collect(category, "article.rsel-item a", e -> e.absUrl("href"));

			for (String url : recipeList) {
				RecipeParser.parse(url);
			}
		}
	}
}
