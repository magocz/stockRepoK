package neldam.strategy;

import java.util.List;
import java.util.Map;

import neldam.share.Share;

public interface Strategy {

	public List<String> think2sale(Map<String, Share> shareMap);

	public List<String> think2buy();

}
