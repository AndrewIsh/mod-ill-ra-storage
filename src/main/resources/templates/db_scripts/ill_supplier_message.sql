ALTER TABLE ill_supplier_message DROP CONSTRAINT IF EXISTS requestid_ill_request_fkey;
ALTER TABLE ill_supplier_message ADD CONSTRAINT requestid_ill_request_fkey_cascade FOREIGN KEY (requestid) REFERENCES ill_request(id) ON DELETE CASCADE;
