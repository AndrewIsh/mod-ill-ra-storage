ALTER TABLE ill_request DROP CONSTRAINT IF EXISTS submissionid_ill_submission_fkey;
ALTER TABLE ill_request ADD CONSTRAINT submissionid_ill_submission_fkey_cascade FOREIGN KEY (submissionid) REFERENCES ill_submission(id) ON DELETE CASCADE;
