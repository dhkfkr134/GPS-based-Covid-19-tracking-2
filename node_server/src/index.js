import { httpso } from './plugin/dash/httpso'
import express from 'express'

const app = express();
app.set('etag', false)

app.get('/location', function(req, res) {
    // [todo] 1. 앱에서 요 스트링을 잘 만들어서 준다.
    const coordination = `37°41'47.5"N 126°50'55.7"E`
    var gps= req.param("gps")
    // [todo] 2. 인코드한다 (encoded를)
    // [todo] 3. 저장한다.
    const encoded = httpso.encode(gps)
    console.log(encoded)
    res.end(encoded)
})

app.listen(3000, () => {
    console.log('app listening at https://localhost:3000')
})