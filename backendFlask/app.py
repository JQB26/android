import os

from flask import Flask, jsonify, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = os.environ.get('DATABASE_URL')
db = SQLAlchemy(app)


class Product(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80), nullable=False)
    price = db.Column(db.Float, nullable=False)
    details = db.Column(db.String(200), nullable=False)
    # category = db.relationship('Category', backref='product', lazy=True)

    def __init__(self, name, price, details):
        self.name = name
        self.price = price
        self.details = details


# class Category(db.Model):
#     id = db.Column(db.Integer, primary_key=True)
#     name = db.Column(db.String(80), nullable=False)
#     keywords = db.Column(db.Text, nullable=False)
#     details = db.Column(db.String(200), nullable=False)
#
#     def __init__(self, name, price, details):
#         self.name = name
#         self.price = price
#         self.details = details


db.create_all()


@app.route('/products/<id>', methods=['GET'])
def get_product(id):
    product = Product.query.get(id)
    del product.__dict__['_sa_instance_state']
    return jsonify(product.__dict__)


@app.route('/products', methods=['GET'])
def get_products():
    products = []
    for product in db.session.query(Product).all():
        del product.__dict__['_sa_instance_state']
        products.append(product.__dict__)
    return jsonify(products)


@app.route('/products', methods=['POST'])
def create_product():
    body = request.get_json()
    db.session.add(Product(body['name'], body['price'], body['details']))
    db.session.commit()
    return "item created"


if __name__ == '__main__':
    app.run(debug=True)
