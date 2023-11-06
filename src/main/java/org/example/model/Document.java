package org.example.model;

import java.time.LocalDate;
import java.util.List;

public class Document {
    private long id;
    private DocumentType documentType;
    private Status status;
    private String uid;
    private String createDate;
    private String endDate;
    private Author author;
    private String description;
    private List<Product> productList;
    private Order order;
    private Document relatedDocument;

    public Document(long id, DocumentType documentType, Status status, String uid, String createDate, String endDate, Author author, String description, List<Product> productList,Order order, Document relatedDocument) {
        this.id = id;
        this.documentType = documentType;
        this.status = status;
        this.uid = uid;
        this.createDate = createDate;
        this.endDate = endDate;
        this.author = author;
        this.description = description;
        this.productList = productList;
        this.order = order;
        this.relatedDocument = relatedDocument;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Document getRelatedDocument() {
        return relatedDocument;
    }

    public void setRelatedDocument(Document relatedDocument) {
        this.relatedDocument = relatedDocument;
    }

}
