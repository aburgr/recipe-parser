package at.burgr.recipeparser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Collector {

	private static final Logger LOG = Logger.getLogger(Collector.class.getName());
	
	public static List<String> collect(String url, String cssPath, Function<Element, String> f) throws Exception {
		List<String> elementList = new ArrayList<>();
		
		LOG.info("Collecting elements from " + url + " with cssPath " + cssPath);
		Document doc = Jsoup.connect(url).get();

		Elements elements = doc.select(cssPath);
		for (Element element : elements) {
			String detail = f.apply(element);			
			
			LOG.info("found " + detail);
			elementList.add(detail);
		}

		return elementList;
	}
}
