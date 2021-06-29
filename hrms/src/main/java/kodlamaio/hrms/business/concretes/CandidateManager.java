package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.verification.abstracts.EmailCheckService;
import kodlamaio.hrms.verification.abstracts.MernisCheckService;

@Service
public class CandidateManager implements CandidateService {
	
	private CandidateDao candidateDao; 
	private MernisCheckService mernisCheckService;
	private EmailCheckService emailCheckService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, MernisCheckService mernisCheckService,
			EmailCheckService emailCheckService) {
		super();
		this.candidateDao = candidateDao;
		this.mernisCheckService = mernisCheckService;
		this.emailCheckService = emailCheckService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "İş arayanlar listelendi.");
	}

	@Override
	public Result add(Candidate candidate) {
		if(candidate.getFirstName() == null || candidate.getLastName() == null
				|| candidate.getEmail() == null || candidate.getPassword() == null
				||candidate.getIdentityNumber() == null || candidate.getBirthYear() == null) 
		{
			
			return new ErrorResult("Tüm alanları doldurunuz!");
		}
		
		else if(!checkEmail(candidate.getEmail())) {
			return new ErrorResult("Email kullanılmaktadır!");
		}
		
		else if(!checkIdentityNumber(candidate.getIdentityNumber())) {
			return new ErrorResult("TC kimlik numarası kullanılmaktadır!");
		}
		
		else if(!this.mernisCheckService.checkIfRealPerson(candidate)) {
			return new ErrorResult("Mernis doğrulamasından geçemedi!");
		}
		
		else if(!this.emailCheckService.CheckIfRealEmail(candidate)) {
			return new ErrorResult("Email gerçek değil!");
		}
		
		this.candidateDao.save(candidate);
		return new SuccessResult("Kayıt başarılı");
		
	}
	
	private boolean checkEmail(String email) {
		if (this.candidateDao.getByEmail(email) == null) {
			return true;
		}
		return false;
	}
	
	private boolean checkIdentityNumber(String identityNumber) {
		if(this.candidateDao.getByIdentityNumber(identityNumber) == null) {
			return true;
		}
		return false;
	}

	
	
	
	
}
