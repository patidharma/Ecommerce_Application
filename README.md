# 🛒 Ecommerce Application

A full-stack eCommerce web application built with **Angular** for the frontend and **Spring Boot** for the backend. The project supports product listing, cart management, order placement, secure payments, and file uploads via AWS S3.

---

## 🚀 Tech Stack

| Layer        | Technology           |
|--------------|----------------------|
| Frontend     | Angular, TypeScript  |
| Backend      | Spring Boot, Java    |
| Database     | MySQL                |
| Payment API  | Razorpay             |
| File Upload  | AWS S3               |
| Build Tools  | Maven, Node.js       |
| Deployment   | GitHub (ready for Render, Netlify, etc.) |

---

## 🔧 Features

- ✅ User registration & login
- 🛍️ Product catalog with categories
- 🛒 Cart functionality (add/remove items)
- 📦 Order placement with confirmation
- 💳 Razorpay payment gateway integration
- ☁️ AWS S3 file upload for product images
- 🔐 Secure environment variable management
- 🌐 RESTful APIs for frontend-backend interaction

---

## 🧪 Project Structure

```bash
Ecommerce_Application/
├── Spring_Backend/          # Spring Boot Backend (REST APIs, DB, Security)
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── application.properties
│
└── angular/                 # Angular Frontend
    ├── src/
    ├── angular.json
    └── package.json
