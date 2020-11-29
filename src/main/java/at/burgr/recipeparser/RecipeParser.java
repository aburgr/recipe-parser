package at.burgr.recipeparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RecipeParser {

	private static final Path OUTPUT_DIRECTORY = Paths.get("recipies");
	private static final Logger LOG = Logger.getLogger(RecipeParser.class.getName());
	
	public static void parse(String url) throws Exception {
		createOutputDirectory();

		LOG.info("Parsing recipe " + url);
		Document doc = Jsoup.connect(url).get();
		LOG.fine(doc.title());
		Elements ingredients = doc.select(".ingredients tbody tr .td-right");
		
		StringBuffer content = new StringBuffer();
		for (Element ingredient : ingredients) {
			content.append(ingredient.text());
			content.append(System.lineSeparator());
		}

		// write content to file
		try	{
			Path file = OUTPUT_DIRECTORY.resolve(removeWrongCharsFromPath(doc.title()) + ".recipe");
			Files.writeString(file, content, StandardOpenOption.CREATE);
		} catch(InvalidPathException ipe)	{
			System.err.println(ipe.getMessage());
		}
	}

	private static void createOutputDirectory() throws IOException {
		if (!Files.exists(OUTPUT_DIRECTORY)) {
			Files.createDirectory(OUTPUT_DIRECTORY);
		}

	}

	private static String removeWrongCharsFromPath(String path) {
		return path.replaceAll("[/|\"*]", "_");
	}
}
