/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author quoct
 */
public class CollectionConnect {

    private Collection collectionId;
    private Question questionId;

    public CollectionConnect(Collection collectionId, Question questionId) {
        this.collectionId = collectionId;
        this.questionId = questionId;
    }

    public CollectionConnect() {
    }

    public Collection getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Collection collectionId) {
        this.collectionId = collectionId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

}
