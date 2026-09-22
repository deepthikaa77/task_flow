import { useState, useEffect } from 'react'
import axios from 'axios'
import './App.css'

const API = 'http://localhost:8080/api'

function useResource(url) {
  const [items, setItems] = useState([])
  const load = () => axios.get(url).then(r => setItems(r.data))
  useEffect(() => { load() }, [url])
  const create = (data) => axios.post(url, data).then(load)
  const remove = (id) => axios.delete(`${url}/${id}`).then(load)
  return { items, create, remove }
}

function Projects() {
  const { items, create, remove } = useResource(`${API}/projects`)
  const [form, setForm] = useState({ projectName: '', description: '', createdByUserId: '' })

  const submit = (e) => {
    e.preventDefault()
    create({ ...form, createdByUserId: Number(form.createdByUserId) })
    setForm({ projectName: '', description: '', createdByUserId: '' })
  }

  return (
    <div>
      <form onSubmit={submit} className="form">
        <input placeholder="Project name" value={form.projectName} onChange={e => setForm({ ...form, projectName: e.target.value })} required />
        <input placeholder="Description" value={form.description} onChange={e => setForm({ ...form, description: e.target.value })} />
        <input placeholder="Creator user ID" type="number" value={form.createdByUserId} onChange={e => setForm({ ...form, createdByUserId: e.target.value })} required />
        <button type="submit">Add Project</button>
      </form>
      <table>
        <thead><tr><th>ID</th><th>Name</th><th>Description</th><th>Creator</th><th></th></tr></thead>
        <tbody>
          {items.map(p => (
            <tr key={p.projectId}>
              <td>{p.projectId}</td><td>{p.projectName}</td><td>{p.description}</td><td>{p.createdByUserId}</td>
              <td><button className="del" onClick={() => remove(p.projectId)}>✕</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

function Tasks() {
  const { items, create, remove } = useResource(`${API}/tasks`)
  const [form, setForm] = useState({ title: '', description: '', status: 'pending', priority: 1, projectId: '', assignedToUserId: '' })

  const submit = (e) => {
    e.preventDefault()
    create({ ...form, priority: Number(form.priority), projectId: Number(form.projectId), assignedToUserId: Number(form.assignedToUserId) })
    setForm({ title: '', description: '', status: 'pending', priority: 1, projectId: '', assignedToUserId: '' })
  }

  return (
    <div>
      <form onSubmit={submit} className="form">
        <input placeholder="Title" value={form.title} onChange={e => setForm({ ...form, title: e.target.value })} required />
        <input placeholder="Description" value={form.description} onChange={e => setForm({ ...form, description: e.target.value })} />
        <select value={form.status} onChange={e => setForm({ ...form, status: e.target.value })}>
          <option value="pending">Pending</option>
          <option value="in_progress">In Progress</option>
          <option value="completed">Completed</option>
        </select>
        <input placeholder="Priority" type="number" min="1" max="5" value={form.priority} onChange={e => setForm({ ...form, priority: e.target.value })} />
        <input placeholder="Project ID" type="number" value={form.projectId} onChange={e => setForm({ ...form, projectId: e.target.value })} required />
        <input placeholder="Assigned user ID" type="number" value={form.assignedToUserId} onChange={e => setForm({ ...form, assignedToUserId: e.target.value })} />
        <button type="submit">Add Task</button>
      </form>
      <table>
        <thead><tr><th>ID</th><th>Title</th><th>Status</th><th>Priority</th><th>Project</th><th>Assigned</th><th></th></tr></thead>
        <tbody>
          {items.map(t => (
            <tr key={t.taskId}>
              <td>{t.taskId}</td><td>{t.title}</td>
              <td><span className={`badge ${t.status}`}>{t.status}</span></td>
              <td>{t.priority}</td><td>{t.projectId}</td><td>{t.assignedToUserId}</td>
              <td><button className="del" onClick={() => remove(t.taskId)}>✕</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

function Users() {
  const { items, create, remove } = useResource(`${API}/users`)
  const [form, setForm] = useState({ username: '', email: '' })

  const submit = (e) => {
    e.preventDefault()
    create(form)
    setForm({ username: '', email: '' })
  }

  return (
    <div>
      <form onSubmit={submit} className="form">
        <input placeholder="Username" value={form.username} onChange={e => setForm({ ...form, username: e.target.value })} required />
        <input placeholder="Email" type="email" value={form.email} onChange={e => setForm({ ...form, email: e.target.value })} required />
        <button type="submit">Add User</button>
      </form>
      <table>
        <thead><tr><th>ID</th><th>Username</th><th>Email</th><th></th></tr></thead>
        <tbody>
          {items.map(u => (
            <tr key={u.userId}>
              <td>{u.userId}</td><td>{u.username}</td><td>{u.email}</td>
              <td><button className="del" onClick={() => remove(u.userId)}>✕</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

const TABS = [
  { label: 'Projects', component: Projects },
  { label: 'Tasks', component: Tasks },
  { label: 'Users', component: Users },
]

export default function App() {
  const [tab, setTab] = useState(0)
  const Tab = TABS[tab].component
  return (
    <div className="app">
      <h1>TaskFlow</h1>
      <nav>
        {TABS.map((t, i) => (
          <button key={t.label} className={i === tab ? 'active' : ''} onClick={() => setTab(i)}>{t.label}</button>
        ))}
      </nav>
      <Tab />
    </div>
  )
}
