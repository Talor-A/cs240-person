
/*************************************************************************
  Winter 2017 CS 240 Programming Exam : Person

 Author: <Talor Anderson>      

 Dependencies: Stack, Queue, Dictionary

 Description:  Models a person, a list of messages that they can
               read, and a list of their friends, so that when you
               post a message, all your friends can read it too.

**************************************************************************/

import java.util.Iterator;

public class Person {
  String name;
  Dictionary<String, Person> contactList;
  VectorStack<String> messages;

  // Create a new Person with this name.
  public Person(String name) {
    this.name = name;
    this.contactList = new Dictionary<String, Person>();
    this.messages = new VectorStack<String>();
  }

  // Make these two people become friends with each other.
  // Throw an exception if you try to meet yourself.
  // We are allowed to assume we didn't meet this person yet.
  public void meet(Person otherPerson) {
    if (otherPerson == this) {
      throw new RuntimeException("cannot try to meet yourself");
    }
    this.contactList.add(otherPerson.name, otherPerson);
    otherPerson.contactList.add(this.name, this);
  }

  // Are these two people friends?
  // Throw an exception if you ask about knowing yourself.
  public boolean knows(Person otherPerson) {
    if (otherPerson == this) {
      throw new RuntimeException("cannot ask about knowing yourself");
    }
    return contactList.contains(otherPerson.name);
  }

  // Post a message to my list and the lists of all my friends
  public void post(String message) {
    Iterator valueIter = this.contactList.getValueIterator();
    while (valueIter.hasNext()) {
      Person contact = (Person) valueIter.next();
      contact.addMessage(message);
    }
    this.addMessage(message);
  }

  public void addMessage(String message) {
    this.messages.push(message);
  }

  // Print a header, then all messages this Person can read, newest first
  public void listMessages() {
    Stack<String> tempStack = new Stack<String>();
    System.out.printf("== The wall of %s ==%n", this.name);
    while (!messages.isEmpty()) {
      String message = messages.pop();
      tempStack.push(message);
      System.out.println(message);
    }
    while (!tempStack.isEmpty()) {
      messages.push(tempStack.pop());
    }
  }
}