package kodlamaio.hrms.verification.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.verification.abstracts.EmailCheckService;

@Service
public class EmailCheckManager implements EmailCheckService {
	
	@Override
	public boolean CheckIfRealEmail(User user) {
		
		return true;
	}
}
