# bluesky-api-client
This is Rest API client for BlueSky API.
Providing helper methods for authentication, fetching timelines, and managing content.

## Requirements
- Docker
- Docker Compose

## Usage
```bash
docker-compose up -d
```

## Rest API Endpoints

### User Login
- **Endpoint:** `/login`
- **Method:** POST
- **Request Body:** JSON object
  - `identifier`: A unique identifier for the user (not null)
  - `password`: The user's password (not null)

### Create Post
- **Endpoint:** `/post`
- **Method:** POST
- **Request Headers:**
  - Authorization: Bearer {token}
  - DID: {did}
  - Handle: {handle}
- **Request Body:** JSON object
    - `text`: String

### Get Author Feed
- **Endpoint:** `/author-feed`
- **Method:** GET
- **Request Headers:**
  - Authorization: Bearer {token}
  - DID: {did}
  - Handle: {handle}

### Get Timeline
- **Endpoint:** `/timeline`
- **Method:** GET
- **Request Headers:**
  - Authorization: Bearer {token}
  - DID: {did}
  - Handle: {handle}

### Get Likes
- **Endpoint:** `/did/{did}/post/{post}/likes`
- **Method:** GET
- **Request Headers:**
  - Authorization: Bearer {token}
  - DID: {did}
  - Handle: {handle}
- **Path Variables:** `did`, `postId`

## WIP
Support Twitter and BlueSky API
