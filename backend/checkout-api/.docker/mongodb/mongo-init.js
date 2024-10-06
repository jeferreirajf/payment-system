db.createUser({
  user: 'develcode',
  pwd: 'develcode',
  roles: ['readWrite', 'dbAdmin'],
});

db = db.getSiblingDB('develcode');

db.log.insertOne({ message: 'Database created.' });

print('Databases created.');
