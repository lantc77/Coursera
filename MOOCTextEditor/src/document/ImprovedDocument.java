package document;

import java.util.ArrayList;
import java.util.List;

public class ImprovedDocument extends Document {

	protected ImprovedDocument(String text) {
		super(text);
	}

	@Override
	public int getNumWords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumSentences() {
		// TODO Auto-generated method stub
		List<String> tokens = getTokens("([!?]+)|(. )");
		return 0;
	}

	@Override
	public int getNumSyllables() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String args[]) {
		testCase(new BasicDocument("This is 7.5. I am king"),
				5, 5, 2);
	}
}
