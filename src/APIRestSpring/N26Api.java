package APIRestSpring;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;




@RestController
@RequestMapping("/N26API")
public class N26Api {

	private SqlMock sqlMock = new SqlMock();
	private Stadistics stadistics = new Stadistics();

	@RequestMapping(value = "/stadistics", method = RequestMethod.GET)
	public ResponseEntity<String> getSomething(@RequestParam(value = "request") String request,	@RequestParam(value = "version", required = false, defaultValue = "1") int version) {
		
		Gson gson = new Gson();
		stadistics.avg = stadistics.sum / stadistics.count;
		return ResponseEntity.ok(gson.toJson(stadistics));
		
	}
	

	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity postSomething(@RequestParam(value = "request") String request,@RequestParam(value = "version", required = false, defaultValue = "1") int version) {

		Gson gson = new Gson();
		Transaction input = gson.fromJson(request, Transaction.class);
		System.out.println("request: " + request);
		System.out.println("Amount: " + input.getAmount());
		System.out.println("Timestamp: " + input.getTimestamp());
		long currentTime = System.currentTimeMillis();
		long minTime = currentTime - 60000;
		System.out.println("Min Timestamp: " + minTime);
		stadistics.newTransaction(input);
		
		new Thread() {
			 public void run() {
				stadistics.newTransactionEvent(input,sqlMock);
			 }
		}.start();
		
		sqlMock.add(input);
		
		if (input.getTimestamp() < minTime){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity(HttpStatus.OK);
		}
	}
}
