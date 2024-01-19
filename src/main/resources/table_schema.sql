CREATE TABLE event_log (
  id varchar(255) NOT NULL,
  date_received timestamp(6) NULL,
  event varchar(255) NULL,
  CONSTRAINT event_log_pkey PRIMARY KEY (id)
);