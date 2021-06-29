package kodlamaio.hrms.verification.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.verification.abstracts.HrmsCheckService;

@Service
public class HrmsCheckManager  implements HrmsCheckService{

	@Override
	public boolean checkIfConfirmHrms(User user) {
		
		return true;
	}
}
