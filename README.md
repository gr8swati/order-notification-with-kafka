# Order Notification System 

## What This Project Does 

Imagine you're building an e-commerce application. When a customer places an order, you want to immediately send them a confirmation email.

But there’s a problem:
Sending an email can take time, and you don’t want your order process to slow down or fail just because the email service is slow or temporarily down.

This project shows how to solve that problem using **Apache Kafka** — the same technology used by companies like Netflix, Uber, and LinkedIn for real-time communication between services.

Instead of directly calling the email service, the system uses Kafka to pass messages between services in a fast and reliable way.

---

## How It Works (Step by Step)

1. **Customer places an order**
   → `POST /api/v1/orders/create`

2. **Order Service**

   * Receives the request
   * Saves the order details in the MySQL database

3. **Order Service sends an event**

   * Creates an `OrderCreated` event
   * Publishes it to Kafka (`order-topic`)

4. **Kafka acts as a bridge**

   * Stores the event
   * Makes it available for other services

5. **Notification Service**

   * Listens to Kafka (`order-topic`)
   * Automatically receives the event

6. **Email is sent**

   * Notification Service sends a confirmation email to the customer 

---

## Flow Diagram (Detailed)

```id="flow2"
Customer 
   ↓
Order Service 
   ↓
MySQL (save order)
   ↓
Kafka (order-topic)
   ↓
Notification Service
   ↓
Email sent (Gmail)
```

---

## Key Magic 

The most important part of this project: **Order Service never calls Notification Service directly**

Instead:

* They communicate through Kafka events
* They are completely independent of each other

---

## Why This Is Important

* If the **Notification Service crashes** → Orders can still be placed without any problem 
* If email sending fails → Kafka can retry the message later 
* The system becomes **faster, more reliable, and scalable**

