package com.algo.pattern.Memento.model;

/**
 * 
 *Originator 创建并在 Memento 对象中存储状态
 */
public class Originator {
   
   private String state;
 
   public void setState(String state){
      this.state = state;
   }
 
   public String getState(){
      return state;
   }
 
   public Memento saveStateToMemento(){
      return new Memento(state);
   }
 
   public void getStateFromMemento(Memento Memento){
      state = Memento.getState();
   }
}
