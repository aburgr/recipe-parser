package at.burgr.recipeparser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RecipeParser {

	public static void parse(String url) throws Exception {
		System.out.println("Parsing recipe " + url);
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc.title());
		System.out.println("---");
		Elements ingredients = doc.select(".ingredients tbody tr .td-right");
		for (Element ingredient : ingredients) {
			System.out.println(ingredient.text());
		}
	}
}
