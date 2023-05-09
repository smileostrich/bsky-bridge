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

### POST
- **Endpoint:** `/login`
  - **Request Body:** JSON object
    - `identifier`: A unique identifier for the user (not null)
    - `password`: The user's password (not null)

- **Endpoint:** `/refresh`
  - **Request Headers:**
    - Authorization: Bearer {token} (caution! this token is refresh token)
    - Did: {did}
    - Handle: {handle}

- **Endpoint:** `/post`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
      - `text`: String

- **Endpoint:** `/repost`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
    - `cid`: String
    - `uri`: String

- **Endpoint:** `/like`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
    - `cid`: String
    - `uri`: String

- **Endpoint:** `/follow`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
    - `did`: String

- **Endpoint:** `/block`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
    - `cid`: String
    - `uri`: String

### DELETE
- **Endpoint:** `/organize/posts`
  - **Path Variables:** `post`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
    - `from`: LocalDateTime

- **Endpoint:** `/post`
  - **Path Variables:** `post`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Request Body:** JSON object
    - `rkey`: String

### GET
- **Endpoint:** `/author-feed`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Query Parameters:**
    - `did`: Optional String (default: current user's DID)
    - `limit`: Optional Integer
    - `cursor`: Optional String

- **Endpoint:** `/timeline`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Query Parameters:**
    - `algorithm`: Optional String
    - `limit`: Optional Integer
    - `cursor`: Optional String

- **Endpoint:** `/did/{did}/post/{post}/likes`
  - **Path Variables:** `did`, `postId`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Query Parameters:**
    - `limit`: Optional Integer
    - `cursor`: Optional String

- **Endpoint:** `/followers`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Query Parameters:**
    - `did`: Optional String (default: current user's DID)
    - `limit`: Optional Integer
    - `cursor`: Optional String

- **Endpoint:** `/follows`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - DID: {did}
    - Handle: {handle}
  - **Query Parameters:**
    - `did`: Optional String (default: current user's DID)
    - `limit`: Optional Integer
    - `cursor`: Optional String

## WIP
Support Twitter and BlueSky API
