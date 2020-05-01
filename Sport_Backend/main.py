import sqlite3

import flask
from flask import request, jsonify

app = flask.Flask(__name__)
app.config["DEBUG"] = True


def dict_factory(cursor, row):
    d = {}
    for idx, col in enumerate(cursor.description):
        d[col[0]] = row[idx]
    return d


@app.route('/', methods=['GET'])
def home():
    return '''<h1>Server is running</h1>'''


@app.route('/api/v1/events/all', methods=['GET'])
def api_all():
    conn = sqlite3.connect('sportify.db')
    conn.row_factory = dict_factory
    cur = conn.cursor()
    all_books = cur.execute('SELECT * FROM events;').fetchall()
    return jsonify(all_books)


@app.route('/api/v1/authenticate', methods=['POST'])
def api_authenticate():
    data = request.json
    username = data['username']
    password = data['password']

    query = "SELECT id, token FROM users WHERE"
    to_filter = []

    if username:
        query += ' username=? AND'
        to_filter.append(username)
    if password:
        query += ' password=? AND'
        to_filter.append(password)
    if not (username or password):
        return "<h1>404</h1><p>The resource could not be found.</p>", 404

    query = query[:-4] + ';'
    conn = sqlite3.connect('sportify.db')
    conn.row_factory = dict_factory
    cur = conn.cursor()
    results = cur.execute(query, to_filter).fetchall()
    if results:
        return jsonify(results[0])
    else:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404


@app.route('/api/v1/event', methods=['GET'])
def api_get_event():
    query_parameters = request.args

    event_id = query_parameters.get('id')

    query = "SELECT * FROM events WHERE"
    to_filter = []

    if event_id:
        query += ' id=? AND'
        to_filter.append(event_id)
    if not event_id:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404

    query = query[:-4] + ';'
    conn = sqlite3.connect('sportify.db')
    conn.row_factory = dict_factory
    cur = conn.cursor()
    results = cur.execute(query, to_filter).fetchall()
    if results:
        return jsonify(results[0])
    else:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404


@app.route('/api/v1/user_info', methods=['GET'])
def api_user_info():
    auth_token = request.headers.get('auth_token')
    query = "SELECT id, username FROM users WHERE"
    to_filter = []

    if auth_token:
        query += ' token=? AND'
        to_filter.append(auth_token)
    if not auth_token:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404

    query = query[:-4] + ';'

    conn = sqlite3.connect('sportify.db')
    conn.row_factory = dict_factory
    cur = conn.cursor()

    results = cur.execute(query, to_filter).fetchall()
    if results:
        return jsonify(results[0])
    else:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404


@app.route('/api/v1/logout', methods=['POST'])
def api_logout():
    auth_token = request.headers.get('auth_token')
    query = "SELECT id FROM users WHERE"
    to_filter = []

    if auth_token:
        query += ' token=? AND'
        to_filter.append(auth_token)
    if not auth_token:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404

    query = query[:-4] + ';'

    conn = sqlite3.connect('sportify.db')
    conn.row_factory = dict_factory
    cur = conn.cursor()

    results = cur.execute(query, to_filter).fetchall()
    if results:
        return jsonify(results[0])
    else:
        return "<h1>404</h1><p>The resource could not be found.</p>", 404


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=3000)
