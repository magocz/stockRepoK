package neldam.stock.tests;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import neldam.share.SharePrice;
import neldam.stock.StockDataBase;

public class StockDatabaseTest {
	@Test
	public void testShoudlCreateDatabaseFromFile() throws IOException {
		Map<String, SharePrice> data = StockDataBase.getDataFrom("../gielda/src/test/resources/testData.csv");
		Assert.assertEquals(5, data.size());
		Assert.assertEquals(3, data.get("PKOBP").getSharePrices().size());
		Assert.assertEquals(2, data.get("KGHM").getSharePrices().size());
		Assert.assertEquals(3, data.get("PGNIG").getSharePrices().size());
		Assert.assertEquals(3, data.get("JSW").getSharePrices().size());
	}

}
