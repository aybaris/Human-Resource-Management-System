package kodlamaio.hrms.verification.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.verification.abstracts.MernisCheckService;

@Service
public class MernisCheckManager  implements MernisCheckService{

	@Override
	public boolean checkIfRealPerson(User user) {
		
		return true;
	}
}
