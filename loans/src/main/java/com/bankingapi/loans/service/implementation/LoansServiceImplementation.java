package com.bankingapi.loans.service.implementation;

import com.bankingapi.loans.constants.LoansConstants;
import com.bankingapi.loans.dto.LoansDTO;
import com.bankingapi.loans.entity.Loans;
import com.bankingapi.loans.exceptions.LoanAlreadyExistsException;
import com.bankingapi.loans.exceptions.ResourceNotFoundException;
import com.bankingapi.loans.mapper.LoansMapper;
import com.bankingapi.loans.repository.LoansRepository;
import com.bankingapi.loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class LoansServiceImplementation implements ILoansService {

    private LoansRepository loansRepository;

    @Override
    public void createLoan(LoansDTO loansDTO) {
        String mobileNumber = loansDTO.getMobileNumber();
        List<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        if (!loans.isEmpty()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber" + mobileNumber);
        }
        loansRepository.save(createNewLoan(loansDTO));
    }

    private Loans createNewLoan(LoansDTO loansDTO) {
        String mobileNumber = loansDTO.getMobileNumber();
        Loans newLoan = new Loans();
        long generatedNewLoanNumber = 1000000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(generatedNewLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(loansDTO.getLoanType());
        newLoan.setTotalLoan(loansDTO.getTotalLoan());
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(loansDTO.getOutstandingAmount());
        return newLoan;
    }

    @Override
    public List<LoansDTO> fetchLoans(String mobileNumber) {
        List<Loans> loans = loansRepository.findByMobileNumber(mobileNumber);
        Stream<LoansDTO> mappedLoans = loans.stream().map((currentLoan) -> LoansMapper.mapToLoansDTO(currentLoan, new LoansDTO()));
        return mappedLoans.toList();
    }

    @Override
    public boolean updateLoan(LoansDTO loansDTO) {
        Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loansDTO.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDTO, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String loanNumber) {
        Optional<Loans> loan = loansRepository.findByLoanNumber(loanNumber);
        if (!loan.isEmpty()) {
            loansRepository.deleteById(loan.get().getLoanId());
            return true;
        }
        return false;
    }
}
