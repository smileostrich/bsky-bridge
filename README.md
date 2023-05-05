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

### GET
- **Endpoint:** `/followers`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}

- **Endpoint:** `/author-feed`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}

- **Endpoint:** `/timeline`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}

- **Endpoint:** `/did/{did}/post/{post}/likes`
  - **Path Variables:** `did`, `postId`
  - **Request Headers:**
    - Authorization: Bearer {token}
    - Did: {did}
    - Handle: {handle}

## WIP
Support Twitter and BlueSky API
