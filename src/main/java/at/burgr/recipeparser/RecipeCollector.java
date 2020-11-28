package at.burgr.recipeparser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RecipeCollector {

	public static List<String> collectFromOverview(String url) throws Exception {
		List<String> recipeUrls = new ArrayList<>();
		System.out.println("Collecting recipes from " + url);
		Document doc = Jsoup.connect(url).get();

		Elements recipies = doc.select("article.rsel-item a");
		for (Element recipe : recipies) {
			String recipeUrl = recipe.absUrl("href");

			System.out.println("found " + recipeUrl);
			recipeUrls.add(recipeUrl);
		}
		return recipeUrls;

	}

}
