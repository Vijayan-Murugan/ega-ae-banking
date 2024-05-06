package com.ega.mapper;

import com.ega.dto.TransactionDto;
import com.ega.model.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto toDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        BeanUtils.copyProperties(transaction,dto);
        dto.setSourceAccountNumber(transaction.getSourceAccount().getAccountNumber());
        if (transaction.getTargetAccount() != null) {
            dto.setTargetAccountNumber(transaction.getTargetAccount().getAccountNumber());
        } else {
            dto.setTargetAccountNumber("N/A");
        }
        return dto;
    }

}
