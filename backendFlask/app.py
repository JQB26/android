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
    category_id = db.Column(db.Integer, db.ForeignKey('category.id'))
    category = db.relationship('Category', backref='products')

    def __init__(self, name, price, details, category_id):
        self.name = name
        self.price = price
        self.details = details
        self.category_id = category_id


class Category(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(80), nullable=False)
    keywords = db.Column(db.Text, nullable=False)
    popularity = db.Column(db.Integer)
    details = db.Column(db.String(200), nullable=False)

    def __init__(self, name, keywords, popularity, details):
        self.name = name
        self.keywords = keywords
        self.popularity = popularity
        self.details = details


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
    db.session.add(Product(body['name'], body['price'], body['details'], body['category_id']))
    db.session.commit()
    return "product created"


@app.route('/categories/<id>', methods=['GET'])
def get_category(id):
    category = Category.query.get(id)
    del category.__dict__['_sa_instance_state']
    return jsonify(category.__dict__)


@app.route('/categories', methods=['GET'])
def get_categories():
    categories = []
    for category in db.session.query(Category).all():
        del category.__dict__['_sa_instance_state']
        categories.append(category.__dict__)
    return jsonify(categories)


@app.route('/categories', methods=['POST'])
def create_category():
    body = request.get_json()
    db.session.add(Category(body['name'], body['keywords'], body['popularity'], body['details']))
    db.session.commit()
    return "category created"


if __name__ == '__main__':
    app.run(debug=True)
