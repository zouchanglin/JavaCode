
package analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.util.Version;
// ”Ô—‘∑÷Œˆ°¢∑÷¥ °¢«–¥ ¿‡


public class CommonAnalyzer extends StandardAnalyzer {
	private CommonAnalyzer(Version version){
		super(version);
	}
	public CommonAnalyzer(){
		this(Version.LUCENE_29);
	}
	
	public TokenStream tokenStream(String fieldName, Reader reader) {
		CommonTokenizer tokenizer=new CommonTokenizer(Version.LUCENE_29,reader);
		tokenizer.setMaxTokenLength(this.getMaxTokenLength());
		TokenStream result = new StandardFilter(tokenizer);
		result = new LowerCaseFilter(result);
		result = new StopFilter(true, result, StandardAnalyzer.STOP_WORDS_SET);
		return result;
	}
	
}
