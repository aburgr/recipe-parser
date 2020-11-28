package at.burgr.recipeparser;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		if(args.length < 1)	{
			System.out.println("Usage: java -jar https://www.chefkoch.de/rs/s0t30/Auflauf-Rezepte.html");
			System.out.println();
			return;
		}
		
		List<String> recipeList = RecipeCollector.collectFromOverview(args[0]);
		
		for(String url : recipeList)	{
			RecipeParser.parse(url);			
		}
	}
}
