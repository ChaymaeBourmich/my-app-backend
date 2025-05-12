package com.example.demo.DTO;

public class OperationRequest {
    private String codeOperation;
    private String operationName;

    // Getters et Setters
    public String getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(String codeOperation) {
        this.codeOperation = codeOperation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
